# Slurm手册

 名称                                                                  | 描述
:----------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------
 命令                                                                  |
 [sacct](https://slurm.schedmd.com/sacct.html)                         | displays accounting data for all jobs and job steps in the Slurm job accounting log or Slurm database
 [sacctmgr](https://slurm.schedmd.com/sacctmgr.html)                   | Used to view and modify Slurm account information.
 [salloc](https://slurm.schedmd.com/salloc.html)                       | Obtain a Slurm job allocation (a set of nodes), execute a command, and then release the allocation when the command is finished.
 [sattach](https://slurm.schedmd.com/sattach.html)                     | Attach to a Slurm job step.
 [sbatch](https://slurm.schedmd.com/sbatch.html)                       | Submit a batch script to Slurm.
 [sbcast](https://slurm.schedmd.com/sbcast.html)                       | transmit a file to the nodes allocated to a Slurm job.
 [scancel](https://slurm.schedmd.com/scancel.html)                     | Used to signal jobs or job steps that are under the control of Slurm.
 [scontrol](https://slurm.schedmd.com/scontrol.html)                   | Used view and modify Slurm configuration and state.
 [sdiag](https://slurm.schedmd.com/sdiag.html)                         | scheduling diagnostic tool.
 [sinfo](https://slurm.schedmd.com/sinfo.html)                         | view information about Slurm nodes and partitions.
 [slurm](https://slurm.schedmd.com/slurm.html)                         | Slurm system overview.
 [smd](https://slurm.schedmd.com/smd.html)                             | failure management support tool.
 [sprio](https://slurm.schedmd.com/sprio.html)                         | view the factors that comprise a job's scheduling priority
 [sh5util](https://slurm.schedmd.com/sh5util.html)                     | merge utility for acct_gather_profile plugin.
 [squeue](https://slurm.schedmd.com/squeue.html)                       | view information about jobs located in the Slurm scheduling queue.
 [sreport](https://slurm.schedmd.com/sreport.html)                     | 查看Slurm核算数据
 [srun](https://slurm.schedmd.com/srun.html)                           | 运行并行作业
 [sshare](https://slurm.schedmd.com/sshare.html)                       | 查看Slurm共享信息
 [sstat](https://slurm.schedmd.com/sstat.html)                         | 查看正在运行的作业或步骤的状态信息
 [strigger](https://slurm.schedmd.com/strigger.html)                   | 设置、获取或清除Slurm触发器信息
 [sview](https://slurm.schedmd.com/sview.html)                         | 查看和修改Slurm状态
 配置文件                                                              |
 [acct_gather.conf](https://slurm.schedmd.com/acct_gather.conf.html)   | 配置acct_gather插件
 [burst_buffer.conf](https://slurm.schedmd.com/burst_buffer.conf.html) | 配置突发缓冲区资源
 [cgroup.conf](https://slurm.schedmd.com/cgroup.conf.html)             | 配置Linux cgroup插件
 [ext_sensors.conf](https://slurm.schedmd.com/ext_sensors.conf.html)   | 配置外部传感器插件
 [gres.conf](https://slurm.schedmd.com/gres.conf.html)                 | 配置计算节点的通用资源
 [knl.conf](https://slurm.schedmd.com/knl.conf.html)                   | 配置Intel Knights Landing处理器
 [nonstop.conf](https://slurm.schedmd.com/nonstop.conf.html)           | 配置容错计算
 [slurm.conf](https://slurm.schedmd.com/slurm.conf.html)               | 配置Slurm
 [slurmdbd.conf](https://slurm.schedmd.com/slurmdbd.conf.html)         | [配置数据库守护进程](configuration/slurmdbd.md)
 [topology.conf](https://slurm.schedmd.com/topology.conf.html)         | 配置集群的网络拓扑结构
 守护进程和其他                                                        |
 [slurmctld](https://slurm.schedmd.com/slurmctld.html)                 | 管理中心守护进程
 [slurmd](https://slurm.schedmd.com/slurmd.html)                       | 计算节点守护进程
 [slurmdbd](https://slurm.schedmd.com/slurmdbd.html)                   | [数据库守护进程](daemon/slurmdbd.md)
 [slurmrestd](https://slurm.schedmd.com/slurmrestd.html)               | REST API守护进程
 [slurmstepd](https://slurm.schedmd.com/slurmstepd.html)               | 任务步骤管理器守护进程
 [SPANK](https://slurm.schedmd.com/spank.html)                         | 管理节点和任务的插件架构




