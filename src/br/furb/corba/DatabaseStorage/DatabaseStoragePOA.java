package br.furb.corba.DatabaseStorage;


/**
* DatabaseStorage/DatabaseStoragePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idl
* Domingo, 22 de Maio de 2016 09h52min06s BRT
*/

public abstract class DatabaseStoragePOA extends org.omg.PortableServer.Servant
 implements DatabaseStorageOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("listMusicsByName", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // DatabaseStorage/DatabaseStorage/listMusicsByName
       {
         String name = in.read_string ();
         org.omg.CORBA.Object $result[] = null;
         $result = this.listMusicsByName (name);
         out = $rh.createReply();
         ObjectArrayHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DatabaseStorage/DatabaseStorage:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DatabaseStorage _this() 
  {
    return DatabaseStorageHelper.narrow(
    super._this_object());
  }

  public DatabaseStorage _this(org.omg.CORBA.ORB orb) 
  {
    return DatabaseStorageHelper.narrow(
    super._this_object(orb));
  }


} // class DatabaseStoragePOA
