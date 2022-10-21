pipeline {
    //def mvnHome
    agent { docker 'apache-maven-3.8.6' }
    tools {
        maven 'apache-maven-3.8.6'
    }
    //参数构建：PROJECT_NAME = "cloud-app@9001;cloud-gateway@9000"
    parameters {
        string(name: 'PROJECT_NAME', defaultValue: 'cloud-app@9001;cloud-gateway@9000', description: '')
        //git凭证ID
        string(name: 'GIT_AUTH', defaultValue: '8db3271f-90c6-42cc-89b7-ecbabea34afa', description: '')
        //git的url地址
        string(name: 'GIT_URL', defaultValue: 'https://github.com/yangcaiwang/cloud-game-grpc.git', description: '')
        //镜像的版本号
        string(name: 'TAG', defaultValue: 'latest', description: '')
        //Harbor的url地址
        string(name: 'HARBOR_URL', defaultValue: '43.138.76.94:85', description: '')
        //镜像库项目名称
        string(name: 'HARBOR_PROJECT', defaultValue: 'cloud-game-grpc', description: '')
        //Harbor的登录凭证ID
        string(name: 'HARBOR_AUTH', defaultValue: '59a2ced5-543b-4443-aa62-581e8b9be4b4', description: '')
    }

    post {
        success {
            echo 'I success!'
        }
        failure {
            echo 'I failed！'
        }
    }
}
