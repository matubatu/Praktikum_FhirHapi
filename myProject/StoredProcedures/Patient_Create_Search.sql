SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_storage('{"resourceType": "Patient"}');

SELECT fhir_create_resource('{"resource": {"resourceType": "Patient", "name": [{"given": ["Smith"]}]}}');

SELECT fhir_search('{"resourceType": "Patient", "queryString": "name=smith&_totalMethod=no"}');