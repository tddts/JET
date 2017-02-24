DELETE FROM ORDER_SEARCH_CACHE
WHERE TYPE_ID IN (
	SELECT DISTINCT ORDER_SEARCH_CACHE.TYPE_ID
	FROM ORDER_SEARCH_CACHE
	INNER JOIN STATIC_TYPES
	ON ORDER_SEARCH_CACHE.TYPE_ID = STATIC_TYPES.TYPE_ID
	AND STATIC_TYPES.VOLUME > :volume);