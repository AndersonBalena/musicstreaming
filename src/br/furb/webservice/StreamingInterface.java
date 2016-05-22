package br.furb.webservice;

import java.util.ArrayList;
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
    ArrayList<Music> listMusicsByName(String name);
    @WebMethod
    ArrayList<Music> listMusicsByArtist(String artist);
    @WebMethod
    String getMusicContent(Music music);
    @WebMethod 
    int updateMusic(MusicDto musicDto);
    @WebMethod
    int removeMusic(Music music);
    @WebMethod
    ArrayList<Music> listMusicsInFolder(String path);
}