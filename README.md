# masterSecu

## Install Istio
https://istio.io/latest/docs/setup/getting-started/
```
cd istio-1.17.0    
export PATH=$PWD/bin:$PATH    
istioctl install --set profile=demo -y
cd ..   
```
Enable auto-injection of the Istio side-cars when the pods are started:
```
kubectl label namespace default istio-injection=enabled
```
Install the Istio addons (Kiali, Prometheus, Jaeger, Grafana):
```
kubectl apply -f samples/addons
```
## 
Enable auto-injection of the Istio side-cars when the pods are started:
```
kubectl label namespace default istio-injection=enabled
```

Configure Docker so that it uses the Kubernetes cluster:
```
minikube docker-env
eval $(minikube -p minikube docker-env)
eval $(minikube docker-env)  
```
# Build Java
```
./gradlew build
```
# Image Docker
```
docker build -f ./Dockerfile -t mastersecu .
```
# Deployment
```
kubectl apply -f deployment.yml
```
# Get the access to the Ingress gateway
```
kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080
```

Get the list of cars to be rented:
```
http://localhost:31380/mastersecu/cars
```

# Manage the cluster with Kiali, Graphana...
```
kubectl -n istio-system port-forward deployment/kiali 20001:20001
```

```
http://localhost:20001/
```

## Launch a workflow when the code is updated

Create a new branch:
```
git branch newcarservice
```
Move to the new branch:
```
git checkout newcarservice
```
Update the code and commit changes:
```
git commit -a -m "newcarservice"
```
Push the changes to GitHub:
```
git push -u origin newcarservice
```
Create a Pull request on GitHub and follow the workflow.

Merge the branch on Github is everything is OK.

Then pull the new main version:

```
git checkout main
```
```
git pull origin main
```

If necessary delete the branch:

```
git branch -D newcarservice
```
```
git push origin --delete newcarservice
```
