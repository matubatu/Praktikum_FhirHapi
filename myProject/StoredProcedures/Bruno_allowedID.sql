SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_resource('{
  "allowId": true, 
  "resource": {
    "resourceType": "Patient", 
    "id": "bruno",
    "name":[{"given":"Bruno"}]}}');


SELECT fhir_search('{"resourceType": "Patient", "queryString": "name=smith&_totalMethod=no"}');