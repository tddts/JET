SELECT
TYPE_ID,
SELL_ORDER_ID,
BUY_ORDER_ID,
SELL_LOCATION,
BUY_LOCATION,
BUY_SECURITY,
SELL_SECURITY,
SELL_PRICE,
BUY_PRICE,
SELL_QUANTITY,
BUY_QUANTITY,
BUY_MIN_QUANTITY,
TRADE_QUANTITY,
PROFIT_PER_UNIT,
PROFIT,
ITEM_VOLUME,
ITEM_CARGO_VOLUME,
ITEM_CARGO_FREE_VOLUME
FROM
    (SELECT
    TYPE_ID,
    SELL_ORDER_ID,
    BUY_ORDER_ID,
    SELL_LOCATION,
    BUY_LOCATION,
    SELL_SECURITY,
    BUY_SECURITY,
    SELL_PRICE,
    BUY_PRICE,
    SELL_QUANTITY,
    BUY_QUANTITY,
    BUY_MIN_QUANTITY,
    TRADE_QUANTITY,
    PROFIT_PER_UNIT,
    ITEM_VOLUME,
    (TRADE_QUANTITY * PROFIT_PER_UNIT) AS PROFIT,
    (TRADE_QUANTITY * ITEM_VOLUME) AS ITEM_CARGO_VOLUME,
    (:cargo_volume - (TRADE_QUANTITY * ITEM_VOLUME)) AS ITEM_CARGO_FREE_VOLUME
    FROM
        (SELECT
        TMP_SELL_ORDERS.TYPE_ID,
        TMP_SELL_ORDERS.ORDER_ID AS SELL_ORDER_ID,
        TMP_BUY_ORDERS.ORDER_ID AS BUY_ORDER_ID,
        TMP_SELL_ORDERS.LOCATION_ID AS SELL_LOCATION,
        TMP_BUY_ORDERS.LOCATION_ID AS BUY_LOCATION,
        TMP_SELL_ORDERS.SECURITY_STATUS AS SELL_SECURITY,
        TMP_BUY_ORDERS.SECURITY_STATUS AS BUY_SECURITY,
        TMP_SELL_ORDERS.PRICE AS SELL_PRICE,
        TMP_BUY_ORDERS.PRICE AS BUY_PRICE,
        TMP_SELL_ORDERS.VOLUME AS SELL_QUANTITY,
        TMP_BUY_ORDERS.VOLUME AS BUY_QUANTITY,
        TMP_BUY_ORDERS.MIN_VOLUME AS BUY_MIN_QUANTITY,
        STATIC_TYPE.VOLUME AS ITEM_VOLUME,
        (CASE WHEN (:cargo_volume / STATIC_TYPE.VOLUME) >= TMP_SELL_ORDERS.VOLUME
        THEN TMP_SELL_ORDERS.VOLUME
        ELSE (:cargo_volume / STATIC_TYPE.VOLUME)
        END) AS TRADE_QUANTITY,
        TMP_BUY_ORDERS.PRICE - TMP_SELL_ORDERS.PRICE - (TMP_SELL_ORDERS.PRICE * :tax_rate) AS PROFIT_PER_UNIT
        FROM TMP_SELL_ORDERS
        INNER JOIN TMP_BUY_ORDERS
            ON TMP_SELL_ORDERS.TYPE_ID = TMP_BUY_ORDERS.TYPE_ID
            AND TMP_SELL_ORDERS.VOLUME >= TMP_BUY_ORDERS.MIN_VOLUME
            AND (TMP_BUY_ORDERS.VOLUME - TMP_SELL_ORDERS.VOLUME) >= 0
            AND (TMP_BUY_ORDERS.PRICE - TMP_SELL_ORDERS.PRICE) > (TMP_SELL_ORDERS.PRICE * :tax_rate)
        INNER JOIN STATIC_TYPE
            ON TMP_SELL_ORDERS.TYPE_ID = STATIC_TYPE.TYPE_ID
            AND TMP_BUY_ORDERS.MIN_VOLUME <= (:cargo_volume / STATIC_TYPE.VOLUME)
        )
    ORDER BY PROFIT DESC
    LIMIT 100
)