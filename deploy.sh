#! /bin/sh
# 接收外部参数
# shellcheck disable=SC2034
docker_username=$1
docker_password=$2
projects=$3
tag=$4

docker login -u ${docker_username} -p ${docker_password}
echo "登陆成功"

# 分割出子模块数组[模块名;端口,模块名;端口]
project_array=(${projects//,/ })
# shellcheck disable=SC2068
for project in ${project_array[@]}; do
  # 分割子模块[模块名;端口]
  module_array=(${project//;/ })
  # 获取模块名
  module_name=${module_array[0]}
  echo "${module_name}"
  # 获取模块端口
  module_port=${module_array[1]}
  echo "${module_port}"

  # 查询容器是否存在，存在则删除
  containerId=$(docker ps -a | grep -w ${module_name} | awk '{print $1}')
  if [ "${containerId}" != "" ]; then
    # 删除容器
    docker rm -f ${containerId}
    echo "成功删除容器"
  fi

  # 查询镜像是否存在，存在则删除
  imageName=${docker_username}/${module_name}:${tag}
  imageId=$(docker images | grep -w ${imageName} | awk '{print $3')

  if [ "${imageId}" != "" ]; then
    # 删除镜像
    docker rmi -f ${imageId}
    echo "成功删除镜像"
  fi

  # 下载镜像
  docker pull ${imageName}
  # 启动容器
  docker run -d --name ${module_name} -p ${module_port}:${module_port} ${imageName}
  echo "${module_name}容器启动成功"
done
