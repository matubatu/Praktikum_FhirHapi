SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_storage('{"resourceType": "Location"}');

SELECT fhir_create_resource('{"resourceType":"Location","status":"active","name":"AKH","mode":"instance"}');
