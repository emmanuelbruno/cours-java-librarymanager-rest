# A not so simple JAX-RS example

## Usage

Compile and launch the REST Server
```shell
git clone --branch jakarta \
  https://github.com/emmanuelbruno/cours-java-librarymanager-rest.git
mvn clean package && \
  mvn exec:java
```

Get a Hello message
```shell
curl -v http://127.0.0.1:9998/myapp/biblio
```

Init the database with two authors
```shell
curl -v -X PUT "http://localhost:9998/myapp/biblio/init"
```

Get author 1 in JSON
```shell
curl -H "Accept: application/json"  \
  http://127.0.0.1:9998/myapp/biblio/auteurs/1
```

Get author 2 in XML
```shell
curl -H "Accept: text/xml"  \
  http://127.0.0.1:9998/myapp/biblio/auteurs/2
```

Get authors in JSON
```shell
curl -H "Accept: application/json"  \
  http://127.0.0.1:9998/myapp/biblio/auteurs
```

Removes all authors
```shell
curl -v -X DELETE "http://localhost:9998/myapp/biblio/"
```

Adds an author
```shell
curl -v -H "Accept: application/json"  \
  -H "Content-type: application/json"  \
  -X POST \
  -d '{"nom":"John","prenom":"Smith","biographie":"My life"}' \
  "http://localhost:9998/myapp/biblio/auteurs/"
```

Fully update an author
```shell
curl -v -H "Accept: application/json"  \
  -H "Content-type: application/json"  \
  -X PUT \
  -d '{"nom":"Martin","prenom":"Jean","biographie":"ma vie"}' \
  "http://localhost:9998/myapp/biblio/auteurs/1"
```

