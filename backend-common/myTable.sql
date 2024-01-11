CREATE TABLE `post` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `title` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
                        `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
                        `tags` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
                        `thumbNum` int NOT NULL DEFAULT '0' COMMENT '点赞数',
                        `favourNum` int NOT NULL DEFAULT '0' COMMENT '收藏数',
                        `userId` bigint NOT NULL COMMENT '创建用户 id',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`),
                        KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='帖子'

CREATE TABLE `post_favour` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `postId` bigint NOT NULL COMMENT '帖子 id',
                               `userId` bigint NOT NULL COMMENT '创建用户 id',
                               `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               KEY `idx_postId` (`postId`),
                               KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帖子收藏'

CREATE TABLE `post_thumb` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `postId` bigint NOT NULL COMMENT '帖子 id',
                              `userId` bigint NOT NULL COMMENT '创建用户 id',
                              `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              KEY `idx_postId` (`postId`),
                              KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帖子点赞'

CREATE TABLE `question` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `title` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
                            `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
                            `tags` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
                            `answer` text COLLATE utf8mb4_unicode_ci COMMENT '题目答案',
                            `submitNum` int NOT NULL DEFAULT '0' COMMENT '题目提交数',
                            `acceptedNum` int NOT NULL DEFAULT '0' COMMENT '题目通过数',
                            `judgeCase` text COLLATE utf8mb4_unicode_ci COMMENT '判题用例（json 数组）',
                            `judgeConfig` text COLLATE utf8mb4_unicode_ci COMMENT '判题配置（json 对象）',
                            `thumbNum` int NOT NULL DEFAULT '0' COMMENT '点赞数',
                            `favourNum` int NOT NULL DEFAULT '0' COMMENT '收藏数',
                            `userId` bigint NOT NULL COMMENT '创建用户 id',
                            `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                            PRIMARY KEY (`id`),
                            KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='题目'

CREATE TABLE `question_submit` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `language` varchar(128) NOT NULL COMMENT '编程语言',
                                   `code` text NOT NULL COMMENT '用户代码',
                                   `judgeInfo` text COMMENT '判题信息（json 对象）',
                                   `status` int NOT NULL DEFAULT '0' COMMENT '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
                                   `questionId` bigint NOT NULL COMMENT '题目 id',
                                   `userId` bigint NOT NULL COMMENT '创建用户 id',
                                   `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                                   PRIMARY KEY (`id`),
                                   KEY `idx_questionId` (`questionId`),
                                   KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目提交'

CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `userAccount` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                        `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                        `unionId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信开放平台id',
                        `mpOpenId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号openId',
                        `userName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
                        `userAvatar` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
                        `userProfile` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户简介',
                        `userRole` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`),
                        KEY `idx_unionId` (`unionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户'

