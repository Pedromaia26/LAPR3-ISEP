-- O navio de destino está devidamente identificado.  *
-- A capacidade disponível do navio foi calculada +
-- corretamente. O aviso é acionado quando necessário +


SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER trg_impedir_excesso_carga 
	BEFORE INSERT ON CARGOMANIFEST_LOAD -- UPDATE
	FOR EACH ROW
DECLARE
	available_capacity_ship INT
	-- max_cap_ship ship.cap%TYPE;
        -- POSSIVEL DATA PARA O MOMENTO ATUAL, PASSAR POR PARAMETRO NO CURSOR, necessario passar por parametro o mmsi ?

CURSOR cur_capacity_available() IS
	    SELECT s.mmsi, (s.cap - (ccm.COUNT(*))) AS CAPACITY_SHIP -- COUNT(*) AS CARGO_MANIFESTS 
		FROM container_cargoManifest ccm
		INNER JOIN cargo_manifest_load cml ON ccm.cargo_manifest_id = cml.id
		INNER JOIN ship s ON cml.mmsi = s.mmsi -- Faltam possivelmente alguma clausula para a data e para o navio que estamos a tratar em especifico (Variaveis?)
BEGIN
	OPEN cur_capacity_available();
        FETCH cur_capacity_available
        INTO available_capacity_ship
        EXIT WHEN cur_capacity_available%notfound;
			IF inserting
			THEN
				IF(available_capacity_ship < 0) -- O valor disponivel no barco está superior ao que se pode ter
				THEN
					raise_application_error(-20501, 'The ships capacity has been exceeded.');
				END IF;
			END IF;    
        CLOSE cur_capacity_available;
END;

/
-- TESTES INSERTS      

