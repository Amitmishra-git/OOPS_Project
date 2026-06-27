param(
    [Parameter(ValueFromRemainingArguments = $true)]
    [string[]]$Arguments
)

$ErrorActionPreference = 'Stop'
$BaseDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$WrapperDir = Join-Path $BaseDir '.mvn\wrapper'
$PropertiesFile = Join-Path $WrapperDir 'maven-wrapper.properties'
$CacheDir = Join-Path $BaseDir '.mvn\apache-maven'
$Version = '3.9.9'
$DownloadUrl = "https://archive.apache.org/dist/maven/maven-3/$Version/binaries/apache-maven-$Version-bin.zip"
$MavenHome = Join-Path $CacheDir "apache-maven-$Version"
$MavenCmd = Join-Path $MavenHome 'bin\mvn.cmd'

if (-not (Test-Path $PropertiesFile)) {
    New-Item -ItemType Directory -Path $WrapperDir -Force | Out-Null
    @"
distributionUrl=$DownloadUrl
"@ | Set-Content -Encoding ASCII $PropertiesFile
}

if (-not (Test-Path $MavenCmd)) {
    New-Item -ItemType Directory -Path $CacheDir -Force | Out-Null
    $ZipFile = Join-Path $CacheDir "apache-maven-$Version-bin.zip"
    if (-not (Test-Path $ZipFile)) {
        Invoke-WebRequest -Uri $DownloadUrl -OutFile $ZipFile
    }
    Expand-Archive -Path $ZipFile -DestinationPath $CacheDir -Force
}

& $MavenCmd @Arguments
exit $LASTEXITCODE