SET plv8.start_proc = 'plv8_init';

SELECT fhir_create_resource('{
  "allowId": true, 
  "resource": {
    "resourceType": "Patient", 
    "id": "smith",
    "name":[{"given":"Bruno"}]}} ');

SELECT fhir_create_resource('{
  "allowId": true,
  "resource":
{"resourceType":"Encounter",
"status": "onleave",
"patient": {"reference": "Patient/smith"}}}'); 