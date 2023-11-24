rootProject.name = "manager"
include("model")
include("redis")
include("redis:configuration")
findProject(":redis:configuration")?.name = "configuration"
include("redis:queue")
findProject(":redis:queue")?.name = "queue"
include("redis:repository")
findProject(":redis:repository")?.name = "repository"
include("processing")
include("controller")
