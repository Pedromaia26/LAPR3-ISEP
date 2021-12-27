-- O navio de destino está devidamente identificado.
-- A capacidade disponível do navio foi calculada 
-- corretamente. O aviso é acionado quando necessário 


SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER trg_prevent_excess_load
	BEFORE INSERT OR UPDATE ON container_cargoManifest
	FOR EACH ROW
DECLARE
	available_capacity_ship INTEGER;
    sh ship.mmsi%TYPE;
	
CURSOR cur_capacity_available IS
	    SELECT (s.cap - (COUNT(ccm.cargo_manifest_id))) INTO available_capacity_ship
		FROM container_cargoManifest ccm
		INNER JOIN cargo_manifest_load cml ON ccm.cargo_manifest_id = cml.id
		INNER JOIN ship s ON cml.ship_mmsi = s.mmsi
		WHERE s.mmsi = sh;
        
BEGIN
	OPEN cur_capacity_available;
        LOOP    
        FETCH cur_capacity_available
        INTO available_capacity_ship;
        EXIT WHEN cur_capacity_available%notfound;
        END LOOP;
        -- CLOSE cur_capacity_available;
        dbms_output.put_line(available_capacity_ship);
				IF(available_capacity_ship < 0) -- The number of cargo manifests is greater than the ship's capacity
				THEN
					raise_application_error(-20501, 'The ships capacity has been exceeded.');
				END IF;           
         CLOSE cur_capacity_available;
END;