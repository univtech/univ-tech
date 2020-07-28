# slurmdbd

https://slurm.schedmd.com/slurmdbd.html

## 名称

slurmdbd - Slurm Database Daemon.

## 概要

slurmdbd [OPTIONS...]

## 描述

slurmdbd provides a secure enterprise-wide interface to a database for Slurm.

This is particularly useful for archiving accounting records.

## 选项

+ -D

  Run slurmdbd in the foreground with logging copied to stdout.

+ -h

  Help; print a brief summary of command options.

+ -n <value>

  Set the daemon's nice value to the specified value, typically a negative number.

+ -R[comma separated cluster name list]

  Reset the lft and rgt values of the associations in the given cluster list.

  Lft and rgt values are used to distinguish hierarchical groups in the slurm accounting database.

  This option should be very rarely used.

+ -v

  Verbose operation.

  Multiple -v's increase verbosity.

+ -V

  Print version information and exit.

## 文件

If slurmdbd is started with the -D option then the core file will be written to the current working directory.

Otherwise if LogFile in "slurmdbd.conf" is a fully qualified path name (starting with a slash), the core file will be written to the same directory as the log file, provided SlurmUser has write permission on the directory.

Otherwise the core file will be written to "/var/tmp/" as a last resort.

If neither of the above directories have write permission for SlurmUser, no core file will be produced.

## 信号

+ SIGTERM SIGINT

  slurmdbd will shutdown cleanly, waiting for in-progress rollups to finish.

+ SIGABRT

  slurmdbd will perform a core dump, then exit.

  In-progress operations are killed.

+ SIGHUP

  Reloads the slurm configuration files, similar to 'scontrol reconfigure'.

+ SIGUSR2

  Reread the log level from the configs, and then reopen the log file.

  This should be used when setting up logrotate.

+ SIGCHLD SIGUSR1 SIGTSTP SIGXCPU SIGQUIT SIGPIPE SIGALRM

  These signals are explicitly ignored.

## 注意

It may be useful to experiment with different slurmctld specific configuration parameters using a distinct configuration file (e.g. timeouts).

However, this special configuration file will not be used by the slurmd daemon or the Slurm programs, unless you specifically tell each of them to use it.

If you desire changing communication ports, the location of the temporary file system, or other parameters used by other Slurm components, change the common configuration file, slurm.conf.




