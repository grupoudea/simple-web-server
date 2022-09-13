# simple-web-server



# Comandos docker

## crear imagen

```shell
docker build --tag jgkserver_i .
```

## Iniciar contenedor

```shell
docker run --name jgkserver_web_con -d -p 8082:8082 jgkserver_i:latest
```

## Remover contenedor y remover imagen

```shell
docker stop jgkserver_web_con & docker rm jgkserver_web_con & docker rmi jgkserver_i
```

# Comandos docker-compose

## Crear imagen
```shell
docker-compose build
```

## Iniciar contenedor

```shell
docker-compose up -d
```



