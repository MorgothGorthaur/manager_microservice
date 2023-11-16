rootProject.name = "manager"
include("model")
include("redis")
include("redis:configuration")
findProject(":redis:configuration")?.name = "configuration"
