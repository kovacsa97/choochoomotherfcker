@ECHO off
for /l %%x in (1, 1, 10) do (
 echo Test %%x
 echo Result:
 java test/Ttest test/expected_output%%x.txt < java main/Main < test/input_cmds%%x.txt
 
 echo.
 echo Actual output:
 java main/Main < test/input_cmds%%x.txt
 echo.
 echo Expected output:
 type test\expected_output%%x.txt
 echo.
 echo.
)
pause