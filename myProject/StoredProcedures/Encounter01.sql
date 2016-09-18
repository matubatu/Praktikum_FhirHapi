SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_storage('{"resourceType": "Encounter"}');

SELECT fhir_create_resource('{"resourceType":"Encounter","encounter":{"reference":"Patient/smith6"}}');

