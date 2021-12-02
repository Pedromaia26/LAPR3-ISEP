CREATE OR REPLACE FUNCTION fncCurrentSituationContainer(ID_CONTAINER INT) 
RETURN sys_refcursor IS 
	currentSituationContainer sys_refcursor;
BEGIN
	OPEN currentSituationContainer FOR
		SELECT c.id, cml.status, s.name, p.name from Container c
		INNER JOIN (SELECT c.id,ccm.id, max(ccm.date) AS LastRegist from Container_cargoManifest ccm
		INNER JOIN Container c on ccm.id = c.id
		GROUP BY c.id, ccm.id)
		INNER JOIN Cargo_Manifest_Load cml on cml.id = Container_cargoManifest.cargo_manifest_id
		INNER JOIN Ship s on cml.ship_mmsi = s.mmsi
		INNER JOIN Port p on cml.port_id = p.id
		WHERE c.id = ID_CONTAINER
		ORDER BY c.id, cml.status, s.name, p.name;


	RETURN currentSituationContainer;
END;
/