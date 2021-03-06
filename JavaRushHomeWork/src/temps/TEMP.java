package temps;

import java.io.IOException;

public class TEMP
{
        /* �������� ������ ��������
    ����� checkTelNumber ������ ���������, �������� �� �������� telNumber �������� ������� ��������.
    �������� ����������:
    1) ���� ����� ���������� � '+', �� �� �������� 12 ����
    2) ���� ����� ���������� � ����� ��� ����������� ������, �� �� �������� 10 ����
    3) ����� ��������� 0-2 ������ '-', ������� �� ����� ���� ������
    4) ����� ��������� 1 ���� ������ '(' � ')'  , ������ ���� ��� ����, �� ��� ����������� ����� ������ '-'
    5) ������ ������ �������� ����� 3 �����
    6) ����� �� �������� ����
    7) ����� ������������� �� �����

    �������:
    +380501234567 - true
    +38(050)1234567 - true
    +38050123-45-67 - true
    050123-4567 - true

    +38)050(1234567 - false
    +38(050)1-23-45-6-7 - false
    050���4567 - false
    050123456 - false
    (0)501234567 - false
    */

    public static void main(String... args) throws IOException
    {
/*        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(080)1234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("0501234565"));
        System.out.println(checkTelNumber("(850)-0309405"));
        System.out.println(checkTelNumber("-1034567890"));
        System.out.println();
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)12-3-45-67"));
        System.out.println(checkTelNumber("050���4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber("05-01(234)567"));
        System.out.println(checkTelNumber("050123-4567c"));
        System.out.println(checkTelNumber("05--01234565"));
        System.out.println(checkTelNumber("+38-(050)1234567"));
        System.out.println(checkTelNumber("+38((050)1234567"));
        System.out.println(checkTelNumber("+5(0--5)1234567"));
        System.out.println(checkTelNumber("7-4-4123689-5"));*/




    }

    public static boolean checkTelNumber(String telNumber)
    {
        boolean finalvalue = false;

        if (telNumber.matches("^\\(\\d{3}\\)[\\-?\\d{6}]{6,8}\\d$")) finalvalue = true;

        if (telNumber.matches("^\\+[[\\(\\d{3}\\)]?\\d\\-{0,2}]{11,15}\\d$")) finalvalue = true;

        if (telNumber.matches("(^\\-{1}?|^\\d{1})[\\d[-]{1,2}?]{8,10}\\d$")) finalvalue = true;

        return finalvalue;
    }
}

