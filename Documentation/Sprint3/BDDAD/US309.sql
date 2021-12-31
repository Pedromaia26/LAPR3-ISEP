create or replace Trigger US309

Before Insert
ON cargo_manifest_load
FOR EACH ROW

DECLARE

    manifest INTEGER;
    flag BOOLEAN:=false;
    ship VARCHAR(9);

Cursor cml IS
    select id
    from cargo_manifest_load
    Where status=1
    And shipmmsi=ship;

    BEGIN

    SELECT shipmmsi into ship
    From cargo_manifest_load
    Where id=:NEW.ID;

    open cml;
    Loop
        FETCH cml INTO manifest;
        EXIT WHEN cml%notfound;

        For loop
        IN(SELECT departure_date, arrival_date
        FROM Stage
        Where cargomanifestloadid=manifest)
        LOOP
            IF :CURRENT_TIMESTAMP > loop.departure_date AND :CURRENT_TIMESTAMP < loop.arrival_date THEN

                flag:= true;


            END IF;
        END LOOP;
    END LOOP;

    IF flag = true THEN
        raise_application_error(-20000,'The ship assiciated to the cargo manifest is not available');
    END IF;

    END;
    
    
create or replace PROCEDURE US309Transaction (manifest_id IN integer, port_id In Integer,ship_mmsi IN Varchar, status IN integer, warehouse_id IN Varchar, truck_id IN integer) IS

    begin

    SET TRANSACTION NAME 'teste';
    Insert into cargo_manifest_load (ID,PORT_ID,SHIP_MMSI,STATUS,WHAREHOUSE_ID,TRUCK_ID) values (manifest_id,port_id,ship_mmsi,status,warehouse_id,truck_id);

    COMMIT;

    exception
        when others then
            rollback;

    END;