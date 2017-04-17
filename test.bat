@ECHO off

for /l %%x in (1, 1, 10) do (
 echo Test %%x
 echo Result:
 java main/Main < test/input_cmds%%x.txt > temp.txt
 java test/Ttest test/expected_output%%x.txt < temp.txt
 echo.
 echo Actual output:
 type temp.txt
 echo.
 echo Expected output:
 type test\expected_output%%x.txt
 echo.
 echo.
 del temp.txt
 pause
 cls
)
