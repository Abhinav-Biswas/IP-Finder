Set WshShell = CreateObject("WScript.Shell") 
WshShell.Run chr(34) & "<Give full path here>/IPFinder.bat" & Chr(34), 0
Set WshShell = Nothing