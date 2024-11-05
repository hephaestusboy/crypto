package javacode;

class paddingscript
{
    public String addpadding(String encrypted)
    {
        encrypted.concat("!");
        return encrypted;
    }
    public String removepadding(String encrypted)
    {
        encrypted = encrypted.replace("!","");
        return encrypted;
    }
}