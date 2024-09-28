-- changeset Bulaukin:1-3
-- Trigger: update_creation_time_trigger

-- DROP TRIGGER IF EXISTS update_creation_time_trigger ON search_engine_schema.page;

CREATE OR REPLACE TRIGGER update_creation_time_trigger
    AFTER INSERT
    ON search_engine_schema.page
    FOR EACH ROW
EXECUTE FUNCTION search_engine_schema.update_creation_time_function();