SET plv8.start_proc = 'plv8_init';



SELECT fhir_search('{"resourceType": "Patient", "queryString": "name=mann&_totalMethod=no"}');