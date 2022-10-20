//git凭证ID
def git_auth = "8db3271f-90c6-42cc-89b7-ecbabea34afa"
//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
//git的url地址
def git_url = "https://github.com/yangcaiwang/cloud-game-grpc.git"
//镜像的版本号
def tag = "latest"
//Harbor的url地址
def harbor_url = "43.138.76.94:85"
//镜像库项目名称
def harbor_project = "cloud-game-grpc"
//Harbor的登录凭证ID
def harbor_auth = "59a2ced5-543b-4443-aa62-581e8b9be4b4"

//参数构建：project_name = "cloud-app@9001;cloud-gateway@9000"
def projectNameArray = "${project_name}".split(";")
pipeline {
   agent any
   stage('拉取代码') {
      checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
      echo '拉取成功'
   }
   stage('编译，安装公共子工程') {
      sh "mvn -f cloud-common clean install"
      sh "mvn -f cloud-datasource clean install"
      sh "mvn -f cloud-web clean install"
      echo '成功编译公共子工程'
   }
   stage('编译，打包微服务工程，上传镜像') {
       for(int i=0;i<${projectNameArray.length};i++){
            def submodule = projectNameArray[i];
            //当前遍历的项目名称
            def submoduleName = "${submodule}".split("@")[0]
            //当前遍历的项目端口
            def submodulePort = "${submodule}".split("@")[1]

            sh "mvn -f ${submoduleName} clean package dockerfile:build"

            //定义镜像名称
            def imageName = "${submoduleName}:${tag}"

            //对镜像打上标签
            sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"

            //把镜像推送到Harbor
            withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
                //登录到Harbor
                sh "docker login -u ${username} -p ${password} ${harbor_url}"

                //镜像上传
                sh "docker push ${harbor_url}/${harbor_project}/${imageName}"

                sh "echo 镜像上传成功"
            }
            //执行部署脚本
            sh "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project $submoduleName $tag $csubmodulePort"
       }
   }
}