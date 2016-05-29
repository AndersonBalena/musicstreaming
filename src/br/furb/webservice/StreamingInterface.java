package br.furb.webservice;

import java.rmi.RemoteException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import br.furb.model.Music;
import br.furb.model.MusicDto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface StreamingInterface {
    @WebMethod 
    Music[] listMusicsByName(String name) throws RemoteException;
    @WebMethod
    Music[] listMusicsByArtist(String artist) throws RemoteException;
    @WebMethod
    double getMusicSize(String path);
    @WebMethod 
    boolean updateMusic(int musicId, MusicDto musicDto) throws RemoteException; 
    @WebMethod
    boolean removeMusic(int musicId) throws RemoteException;
    @WebMethod
    String listMusicsInFolder(String path);
    @WebMethod
    Music getMusicById(int id) throws RemoteException;
    @WebMethod
    Music getLastPlayedMusic();
}