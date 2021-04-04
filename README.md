# springAwsS3

## instalando S3Ninja

Entre na pasta s3ninja

![alt text](https://i.imgur.com/cIGtLXQ.png)

execute o seguinte comando no terminal:

docker-compose -f s3ninja.yml up

## Executando o S3Ninja e criando um Bucket

para abrir o S3Ninja acesse o seguinte link

http://localhost:9444


para criar o bucket siga a imagem abaixo
crie um bucket com nome de bucket


![alt text](https://i.imgur.com/G5qfMmU.png)


## EndPoints


### upload
form-dataName = file

![alt text](https://i.imgur.com/Qjnb29t.png)

http://localhost:8081/s3/upload

### delete

http://localhost:8081/s3/delete/{nomeDoArquivo}

### Downloads

#### Download imagem

http://localhost:8081/s3/downloadJPG/{nomeDoArquivo}

#### Download pdf

http://localhost:8081/s3/downloadPDF/{nomeDoArquivo}


#### Download mp3

http://localhost:8081/s3/downloadMP3/{nomeDoArquivo}

#### Download mp4

http://localhost:8081/s3/video/{nomeDoArquivo}





