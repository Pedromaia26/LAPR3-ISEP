Select cml.status, s.name, p.name from Container c
inner join (select c.id,ccm.id, max(ccm.date) AS LastRegist from Container_cargoManifest ccm
inner join Container c on ccm.id = c.id
group by c.id, ccm.id)
inner join Cargo_Manifest_Load cml on cml.id = Container_cargoManifest.cargo_manifest_id
inner join Ship s on cml.ship_mmsi = s.mmsi
inner join Port p on cml.port_id = p.id (Possivelmente terá de se criar um inner join com select para ir buscar o name do port, tentar tirar a duvida)
where c.id = 3
order by cml.status, s.name, p.name;