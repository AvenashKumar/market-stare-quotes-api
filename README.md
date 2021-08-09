
===========================Backend========================
## Backend:

### Run Cloud Run:

1. Build/Create tag from DockerFile:
	us.gcr.io/marketstare/backend-alphavantage-search:<Version>
	
	e.g.
	us.gcr.io/marketstare/backend-marketstare-search:1.0.0
	
2. docker push us.gcr.io/marketstare/backend-marketstare-search:1.0.0 (credentials login gcloud, follow instruction on command line, if getting error)

3. Create new service in CloudRun and use newly created image.

For more details:
https://cloud.google.com/run/docs/quickstarts#containerizing


===============================Updating API gate openapi2 yaml file=============
- List all api configuration:
gcloud api-gateway api-configs list --project=marketstare

- Create new configuration:
gcloud api-gateway api-configs create <new-api-config-name> --api=backend-alphavantage-search --openapi-spec=openapi2-functions.yaml --project=marketstare --backend-auth-service-account=user-marketstare-admin-gateway@marketstare.iam.gserviceaccount.com

- Update gateway
gcloud api-gateway gateways update gateway-marketstare --api=backend-alphavantage-search --api-config=<new-api-config-name> --location=us-central1 --project=marketstare

- Test your gateway endpoints

- Delete old configuration:
 gcloud api-gateway api-configs delete <api-config-name> --api=backend-alphavantage-search --project=marketstare