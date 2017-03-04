DELETE FROM ORDER_SEARCH_CACHE WHERE ORDER_PK IN (
    SELECT MIN(ORDER_PK) AS PK FROM ORDER_SEARCH_CACHE
        WHERE ORDER_ID IN (
            SELECT ORDER_ID FROM (
                SELECT ORDER_ID, COUNT(ORDER_ID) AS CNT FROM ORDER_SEARCH_CACHE GROUP BY ORDER_ID)
                WHERE CNT > 1)
    GROUP BY ORDER_ID);