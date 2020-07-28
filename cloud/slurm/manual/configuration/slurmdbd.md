# slurmdbd.conf

https://slurm.schedmd.com/slurmdbd.conf.html

## 名称

slurmdbd.conf - Slurm Database Daemon (SlurmDBD) configuration file

## 描述

slurmdb.conf is an ASCII file which describes Slurm Database Daemon (SlurmDBD) configuration information.

The file location can be modified at system build time using the DEFAULT_SLURM_CONF parameter or at execution time by setting the SLURM_CONF environment variable.

The contents of the file are case insensitive except for the names of nodes and files.

Any text following a "#" in the configuration file is treated as a comment through the end of that line.

Changes to the configuration file take effect upon restart of SlurmDbd or daemon receipt of the SIGHUP signal unless otherwise noted.

This file should be only on the computer where SlurmDBD executes and should only be readable by the user which executes SlurmDBD (e.g. "slurm").

If the slurmdbd daemon is started as user root and changes to another user ID, the configuration file will initially be read as user root, but will be read as the other user ID in response to a SIGHUP signal.

This file should be protected from unauthorized access since it contains a database password.

The overall configuration parameters available include:

```
ArchiveDir
    If ArchiveScript is not set the slurmdbd will generate a file that can be read in anytime with sacctmgr load filename.
    This directory is where the file will be placed after a purge event has happened and archive for that element is set to true.
    Default is /tmp.
    The format for this files name is $ArchiveDir/$ClusterName_$ArchiveObject_archive_$BeginTimeStamp_$endTimeStamp We limit archive files to 50000 records per file.
    If more than 50000 records exist during that time period, they will be written to a new file.
    Subsequent archive files during the same time period will have ".<number>" appended to the file, for example .2, with the number increasing by one for each file in the same time period.

ArchiveEvents
    When purging events also archive them.
    Boolean, yes to archive event data, no otherwise.
    Default is no.

ArchiveJobs
    When purging jobs also archive them.
    Boolean, yes to archive job data, no otherwise.
    Default is no.

ArchiveResvs
    When purging reservations also archive them.
    Boolean, yes to archive reservation data, no otherwise.
    Default is no.

ArchiveScript
    This script can be executed every time a rollup happens (every hour, day and month), depending on the Purge*After options.
    This script is used to transfer accounting records out of the database into an archive.
    It is used in place of the internal process used to archive objects.
    The script is executed with a no arguments, The following environment variables are set.

    SLURM_ARCHIVE_EVENTS          1 for archive events 0 otherwise.
    SLURM_ARCHIVE_LAST_EVENT      Time of last event start to archive.
    SLURM_ARCHIVE_JOBS            1 for archive jobs 0 otherwise.
    SLURM_ARCHIVE_LAST_JOB        Time of last job submit to archive.
    SLURM_ARCHIVE_STEPS           1 for archive steps 0 otherwise.
    SLURM_ARCHIVE_LAST_STEP       Time of last step start to archive.
    SLURM_ARCHIVE_SUSPEND         1 for archive suspend data 0 otherwise.
    SLURM_ARCHIVE_TXN             1 for archive transaction data 0 otherwise.
    SLURM_ARCHIVE_USAGE           1 for archive usage data 0 otherwise.
    SLURM_ARCHIVE_LAST_SUSPEND    Time of last suspend start to archive.

ArchiveSteps
    When purging steps also archive them.
    Boolean, yes to archive step data, no otherwise.
    Default is no.

ArchiveSuspend
    When purging suspend data also archive it.
    Boolean, yes to archive suspend data, no otherwise.
    Default is no.

ArchiveTXN
    When purging transaction data also archive it.
    Boolean, yes to archive transaction data, no otherwise.
    Default is no.

ArchiveUsage
    When purging usage data (Cluster, Association and WCKey) also archive it.
    Boolean, yes to archive transaction data, no otherwise.
    Default is no.

AuthInfo
    Additional information to be used for authentication of communications with the Slurm control daemon (slurmctld) on each cluster.
    The interpretation of this option is specific to the configured AuthType.
    In the case of auth/munge, this can be configured to use a Munge daemon specifically configured to provide authentication between clusters while the default Munge daemon provides authentication within a cluster.
    In that case, this will specify the pathname of the socket to use.
    Per default this value is left unspecified, which results in the default authentication mechanism being used.

AuthAltTypes
    Command separated list of alternative authentication plugins that the slurmdbd will permit for communication.

AuthType
    Define the authentication method for communications between Slurm components.
    Acceptable values at present include "auth/none" and "auth/munge".
    The default value is "auth/munge".
    Do not use "auth/none" if you desire any security.
    "auth/munge" indicates that LLNL's MUNGE system is to be used (this is the supported authentication mechanism for Slurm; see "https://dun.github.io/munge/" for more information).
    SlurmDBD must be terminated prior to changing the value of AuthType and later restarted.

CommitDelay
    How many seconds between commits on a connection from a Slurmctld.
    This speeds up inserts into the database dramatically.
    If you are running a very high throughput of jobs you should consider setting this.
    In testing, 1 second improves the slurmdbd performance dramatically and reduces overhead.
    There is a small probability of data loss though since this creates a window in which if the slurmdbd seg faults or exits abnormally for any reason the data not committed could be lost.
    While this situation should be very rare, it does present an extremely small risk, but may be the only way to run in extremely heavy environments.
    In all honesty, the risk is quite low, but still present.

DbdBackupHost
    The short, or long, name of the machine where the backup Slurm Database Daemon is executed (i.e. the name returned by the command "hostname -s").
    This host must have access to the same underlying database specified by the 'Storage' options mentioned below.

DbdAddr
    Name that DbdHost should be referred to in establishing a communications path.
    This name will be used as an argument to the gethostbyname() function for identification.
    For example, "elx0000" might be used to designate the Ethernet address for node "lx0000".
    By default the DbdAddr will be identical in value to DbdHost.

DbdHost
    The short, or long, name of the machine where the Slurm Database Daemon is executed (i.e. the name returned by the command "hostname -s").
    This value must be specified.

DbdPort
    The port number that the Slurm Database Daemon (slurmdbd) listens to for work.
    The default value is SLURMDBD_PORT as established at system build time.
    If no value is explicitly specified, it will be set to 6819.
    This value must be equal to the AccountingStoragePort parameter in the slurm.conf file.

DebugFlags
    Defines specific subsystems which should provide more detailed event logging.
    Multiple subsystems can be specified with comma separators.
    Most DebugFlags will result in verbose logging for the identified subsystems and could impact performance.
    Valid subsystems available today (with more to come) include:

    DB_ARCHIVE        SQL statements/queries when dealing with archiving and purging the database.
    DB_ASSOC          SQL statements/queries when dealing with associations in the database.
    DB_EVENT          SQL statements/queries when dealing with (node) events in the database.
    DB_JOB            SQL statements/queries when dealing with jobs in the database.
    DB_QOS            SQL statements/queries when dealing with QOS in the database.
    DB_QUERY          SQL statements/queries when dealing with transactions and such in the database.
    DB_RESERVATION    SQL statements/queries when dealing with reservations in the database.
    DB_RESOURCE       SQL statements/queries when dealing with resources like licenses in the database.
    DB_STEP           SQL statements/queries when dealing with steps in the database.
    DB_TRES           SQL statements/queries when dealing with trackable resources in the database.
    DB_USAGE          SQL statements/queries when dealing with usage queries and inserts in the database.
    DB_WCKEY          SQL statements/queries when dealing with wckeys in the database.
    FEDERATION        SQL statements/queries when dealing with federations in the database.

DebugLevel
    The level of detail to provide the Slurm Database Daemon's logs.
    The default value is info.

    quiet      Log nothing
    fatal      Log only fatal errors
    error      Log only errors
    info       Log errors and general informational messages
    verbose    Log errors and verbose informational messages
    debug      Log errors and verbose informational messages and debugging messages
    debug2     Log errors and verbose informational messages and more debugging messages
    debug3     Log errors and verbose informational messages and even more debugging messages
    debug4     Log errors and verbose informational messages and even more debugging messages
    debug5     Log errors and verbose informational messages and even more debugging messages

DebugLevelSyslog
    The slurmdbd daemon will log events to the syslog file at the specified level of detail.
    If not set, the slurmdbd daemon will log to syslog at level fatal, unless there is no LogFile and it is running in the background, in which case it will log to syslog at the level specified by DebugLevel (at fatal in the case that DebugLevel is set to quiet) or it is run in the foreground, when it will be set to quiet.

    quiet      Log nothing
    fatal      Log only fatal errors
    error      Log only errors
    info       Log errors and general informational messages
    verbose    Log errors and verbose informational messages
    debug      Log errors and verbose informational messages and debugging messages
    debug2     Log errors and verbose informational messages and more debugging messages
    debug3     Log errors and verbose informational messages and even more debugging messages
    debug4     Log errors and verbose informational messages and even more debugging messages
    debug5     Log errors and verbose informational messages and even more debugging messages

DefaultQOS
    When adding a new cluster this will be used as the qos for the cluster unless something is explicitly set by the admin with the create.

LogFile
    Fully qualified pathname of a file into which the Slurm Database Daemon's logs are written.
    The default value is none (performs logging via syslog).
    See the section LOGGING in the slurm.conf man page if a pathname is specified.

LogTimeFormat
    Format of the timestamp in slurmdbd log files.
    Accepted values are "iso8601", "iso8601_ms", "rfc5424", "rfc5424_ms", "clock", and "short".
    The values ending in "_ms" differ from the ones without in that fractional seconds with millisecond precision are printed.
    The default value is "iso8601_ms".
    The "rfc5424" formats are the same as the "iso8601" formats except that the timezone value is also shown.
    The "clock" format shows a timestamp in microseconds retrieved with the C standard clock() function.
    The "short" format is a short date and time format.
    The "thread_id" format shows the timestamp in the C standard ctime() function form without the year but including the microseconds, the daemon's process ID and the current thread ID.

MaxQueryTimeRange
    Return an error if a query is against too large of a time span, to prevent ill-formed queries from causing performance problems within SlurmDBD.
    Default value is INFINITE which allows any queries to proceed.
    Accepted time formats are the same as the MaxTime option in slurm.conf.
    User SlurmUser and root are exempt from this restriction.
    Note that queries which attempt to return over 3GB of data will still fail to complete with ESLURM_RESULT_TOO_LARGE.

MessageTimeout
    Time permitted for a round-trip communication to complete in seconds.
    Default value is 10 seconds.

Parameters
    Contains arbitrary comma separated parameters used to alter the behavior of the slurmdbd.

    PreserveCaseUser    When defining users do not force lower case which is the default behavior.

PidFile
    Fully qualified pathname of a file into which the Slurm Database Daemon may write its process ID.
    This may be used for automated signal processing.
    The default value is "/var/run/slurmdbd.pid".

PluginDir
    Identifies the places in which to look for Slurm plugins.
    This is a colon-separated list of directories, like the PATH environment variable.
    The default value is "/usr/local/lib/slurm".

PrivateData
    This controls what type of information is hidden from regular users.
    By default, all information is visible to all users.
    User SlurmUser, root, and users with AdminLevel=Admin can always view all information.
    Multiple values may be specified with a comma separator.
    Acceptable values include:

    accounts        prevents users from viewing any account definitions unless they are coordinators of them.
    events          prevents users from viewing event information unless they have operator status or above.
    jobs            prevents users from viewing job records belonging to other users unless they are coordinators of the account running the job when using sacct.
    reservations    restricts getting reservation information to users with operator status and above.
    usage           prevents users from viewing usage of any other user.
                    This applys to sreport.
    users           prevents users from viewing information of any user other than themselves, this also makes it so users can only see associations they deal with.
                    Coordinators can see associations of all users in the account they are coordinator of, but can only see themselves when listing users.

PurgeEventAfter
    Events happening on the cluster over this age are purged from the database.
    This includes node down times and such.
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job step records are never purged.

PurgeJobAfter
    Individual job records over this age are purged from the database.
    Aggregated information will be preserved to "PurgeUsageAfter".
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job records are never purged.

PurgeResvAfter
    Individual reservation records over this age are purged from the database.
    Aggregated information will be preserved to "PurgeUsageAfter".
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then reservation records are never purged.

PurgeStepAfter
    Individual job step records over this age are purged from the database.
    Aggregated information will be preserved to "PurgeUsageAfter".
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job step records are never purged.

PurgeSuspendAfter
    Records of individual suspend times for jobs over this age are purged from the database.
    Aggregated information will be preserved to "PurgeUsageAfter".
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job step records are never purged.

PurgeTXNAfter
    Records of individual transaction times for transactions over this age are purged from the database.
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job step records are never purged.

PurgeUsageAfter
    Usage Records (Cluster, Association and WCKey) over this age are purged from the database.
    The time is a numeric value and is a number of months.
    If you want to purge more often you can include "hours", or "days" behind the numeric value to get those more frequent purges (i.e. a value of "12hours" would purge everything older than 12 hours).
    The purge takes place at the start of the each purge interval.
    For example, if the purge time is 2 months, the purge would happen at the beginning of each month.
    If not set (default), then job step records are never purged.

SlurmUser
    The name of the user that the slurmdbd daemon executes as.
    This user must exist on the machine executing the Slurm Database Daemon and have the same UID as the hosts on which slurmctld execute.
    For security purposes, a user other than "root" is recommended.
    The default value is "root".
    This name should also be the same SlurmUser on all clusters reporting to the SlurmDBD.
    NOTE: If this user is different from the one set for slurmctld and is not root, it must be added to accounting with AdminLevel=Admin and slurmctld must be restarted.

StorageHost
    Define the name of the host the database is running where we are going to store the data.
    Ideally this should be the host on which slurmdbd executes.

StorageBackupHost
    Define the name of the backup host the database is running where we are going to store the data.
    This can be viewed as a backup solution when the StorageHost is not responding.
    It is up to the backup solution to enforce the coherency of the accounting information between the two hosts.
    With clustered database solutions (active/passive HA), you would not need to use this feature.
    Default is none.

StorageLoc
    Specify the name of the database as the location where accounting records are written.
    Defaults to "slurm_acct_db".

StoragePass
    Define the password used to gain access to the database to store the job accounting data.
    The '#' character is not permitted in a password.

StoragePort
    The port number that the Slurm Database Daemon (slurmdbd) communicates with the database.

StorageType
    Define the accounting storage mechanism type.
    Acceptable values at present include "accounting_storage/mysql".
    The value "accounting_storage/mysql" indicates that accounting records should be written to a MySQL or MariaDB database specified by the StorageLoc parameter.
    This value must be specified.

StorageUser
    Define the name of the user we are going to connect to the database with to store the job accounting data.

TCPTimeout
    Time permitted for TCP connection to be established.
    Default value is 2 seconds.

TrackSlurmctldDown
    Boolean yes or no.
    If set the slurmdbd will mark all idle resources on the cluster as down when a slurmctld disconnects or is no longer reachable.
    The default is no.

TrackWCKey
    Boolean yes or no.
    Used to set display and track of the Workload Characterization Key.
    Must be set to track wckey usage.
    This must be set to generate rolled up usage tables from WCKeys.
    NOTE: If TrackWCKey is set here and not in your various slurm.conf files all jobs will be attributed to their default WCKey.
```

## 示例

```
#
# Sample /etc/slurmdbd.conf
#
ArchiveEvents=yes
ArchiveJobs=yes
ArchiveResvs=yes
ArchiveSteps=no
ArchiveSuspend=no
ArchiveTXN=no
ArchiveUsage=no
#ArchiveScript=/usr/sbin/slurm.dbd.archive
AuthInfo=/var/run/munge/munge.socket.2
AuthType=auth/munge
DbdHost=db_host
DebugLevel=info
PurgeEventAfter=1month
PurgeJobAfter=12month
PurgeResvAfter=1month
PurgeStepAfter=1month
PurgeSuspendAfter=1month
PurgeTXNAfter=12month
PurgeUsageAfter=24month
LogFile=/var/log/slurmdbd.log
PidFile=/var/tmp/jette/slurmdbd.pid
SlurmUser=slurm_mgr
StoragePass=shazaam
StorageType=accounting_storage/mysql
StorageUser=database_mgr
```

## 文件

/etc/slurmdbd.conf




