-- -- changeSet Bulaukin:1-1
-- FUNCTION: search_engine_schema.update_creation_time_function()

-- DROP FUNCTION IF EXISTS search_engine_schema.update_creation_time_function();

CREATE OR REPLACE FUNCTION search_engine_schema.update_creation_time_function()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
    curr_time   TIMESTAMP := NOW();
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE search_engine_schema.site SET status_time = curr_time WHERE id = new.site_id;
    END IF;
    RETURN NEW;
EXCEPTION
    WHEN foreign_key_violation THEN
        RETURN NULL;
END
$BODY$;

ALTER FUNCTION search_engine_schema.update_creation_time_function()
    OWNER TO postgres;
