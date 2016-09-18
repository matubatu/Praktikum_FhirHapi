SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_resource('{
  "allowId": true, 
  "resource": {
    "resourceType": "Patient", 
    "id": "Smith",
    "name":[{"given":"Bruno"}, {"family":"Smith"}]}}');

