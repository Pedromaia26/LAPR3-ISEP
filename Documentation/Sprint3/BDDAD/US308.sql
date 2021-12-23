-- O navio de destino está devidamente identificado.  *
-- A capacidade disponível do navio foi calculada +
-- corretamente. O aviso é acionado quando necessário +


SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER trg_prevent_excess_load
	BEFORE INSERT ON CARGOMANIFEST_LOAD
	FOR EACH ROW
DECLARE
	available_capacity_ship INTEGER;
	
CURSOR cur_capacity_available IS
	    SELECT s.mmsi, (s.cap - (ccm.COUNT(cargo_manifest_id))) AS CAPACITY_SHIP -- COUNT(*) AS CARGO_MANIFESTS 
		FROM container_cargoManifest ccm
		INNER JOIN cargo_manifest_load cml ON ccm.cargo_manifest_id = cml.id
		INNER JOIN ship s ON cml.mmsi = s.mmsi
		WHERE CAPACITY_SHIP = available_capacity_ship;
BEGIN
	OPEN cur_capacity_available();
        FETCH cur_capacity_available
        INTO available_capacity_ship;
        EXIT WHEN cur_capacity_available%notfound;
			IF inserting
			THEN
				IF(available_capacity_ship < 0) -- The number of cargo manifests is greater than the ship's capacity
				THEN
					raise_application_error(-20501, 'The ships capacity has been exceeded.');
				END IF;
			END IF;    
        CLOSE cur_capacity_available;
END;
/