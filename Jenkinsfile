sh "echo 进入流水线脚本"
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

node {
   //获取当前选择的项目名称
   def selectedProjectNames = "${project_name}".split(",")

   stage('拉取代码') {
      checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
   }
      /*  stage('代码审查') {
            for(int i=0;i<selectedProjectNames.length;i++){
                //tensquare_eureka_server@10086
                def projectInfo = selectedProjectNames[i];
                //当前遍历的项目名称
                def currentProjectName = "${projectInfo}".split("@")[0]
                //当前遍历的项目端口
                def currentProjectPort = "${projectInfo}".split("@")[1]

                //定义当前Jenkins的SonarQubeScanner工具
                def scannerHome = tool 'sonar-scanner'
                //引用当前JenkinsSonarQube环境
                withSonarQubeEnv('sonarqube') {
                     sh """
                             cd ${currentProjectName}
                             ${scannerHome}/bin/sonar-scanner
                     """
                }
            }
       } */
   stage('编译，安装公共子工程') {
      sh "mvn -f cloud-common clean install"
      sh "mvn -f cloud-datasource clean install"
      sh "mvn -f cloud-web clean install"
   }
   stage('编译，打包微服务工程，上传镜像') {
       for(int i=0;i<selectedProjectNames.length;i++){
            //tensquare_eureka_server@10086
            def projectInfo = selectedProjectNames[i];
            //当前遍历的项目名称
            def currentProjectName = "${projectInfo}".split("@")[0]
            //当前遍历的项目端口
            def currentProjectPort = "${projectInfo}".split("@")[1]

            sh "mvn -f ${currentProjectName} clean package dockerfile:build"

            //定义镜像名称
            def imageName = "${currentProjectName}:${tag}"

            //对镜像打上标签
            sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"

            //把镜像推送到Harbor
            withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {

            //登录到Harbor
            sh "docker login -u ${username} -p ${password} ${harbor_url}"

            //镜像上传
            sh "docker push ${harbor_url}/${harbor_project}/${imageName}"

            sh "echo 镜像上传成功"

            //部署应用
            sh "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project $currentProjectName $tag $currentProjectPort $activeProfile"
       }
   }
}