-- US312 Como cliente, desejo saber a situação atual de um determinado contêiner sendo utilizado para o transporte de 
minhas mercadorias - US204.

CREATE OR REPLACE TRIGGER US312
BEFORE INSERT OR UPDATE ON cargo_manifest_load 
FOR EACH ROW
DECLARE
	id_container INTEGER;
	output VARCHAR(255);
	username VARCHAR(255);
	id_invalid EXCEPTION;
	cont_not_rented EXCEPTION;

BEGIN

	US204(id_container, username, output);

	IF NOT EXISTS(
 	   SELECT count(*) FROM cargo_manifest_load
	   WHERE id = :NEW.id_container) THEN
	      RAISE id_invalid;
	   ELSE IF( 
	      SELECT cml.id, ut.username from cargo_manifest_load cml
	      INNER JOIN Container_cargomanifest ccm ON cml.id = ccm.cargo_manifest_id
	      INNER JOIN User_table ut ON ccm.userid_client = ut.username
	      WHERE ut.username != :NEW.username) THEN
		 RAISE cont_not_rented;
	    END IF;
	END IF;
                           
EXCEPTION
    WHEN id_invalid THEN
	raise_application_error(-10, 'The id is invalid');
    WHEN cont_not_rented THEN
 	raise_application_error(-11, 'The container is not rented by the client');
END;
/