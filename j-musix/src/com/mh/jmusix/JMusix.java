package com.mh.jmusix;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;



public class JMusix extends AndroidNonvisibleComponent {

  private MusixMatch musixMatch;
  private Track track;
  private TrackData data;
  private Lyrics lyrics;

  // api = 346009e20afcc6c8c458969761340e91;

  private String API;
  private String  Track_Name = "Don't stop the Party";
  private String Artist_Name = "The Black Eyed Peas";

  //////// Track Search [ Fuzzy ]
    private int albumID;
    private String albumName;
    private int artistID;
    private String artistName;
    private int trackID;

  ///Getting Lyrics
  private int lyricsID;
  private String lyricsLanguage;
  private String lyricsBody;
  private String scriptTrackingURL;
  private String pixelTrackingURL;
  private String lyricsCopyright;



  public JMusix(ComponentContainer container) {
    super(container.$form());

  }

  //////////////////////
  @SimpleProperty
  @DesignerProperty(editorType = "text",defaultValue = "API KAY")
  public void ApiKay(String Api){
    API = Api;
  }
  @SimpleProperty
  public String ApiKay() {
    return API;
  }

  ///////////////////////////
  @SimpleProperty
  @DesignerProperty(editorType = "text",defaultValue = "Don't stop the Party")
  public void TrackName(String track_Name){
    Track_Name = track_Name;
  }
  @SimpleProperty
  public String TrackName(){
    return Track_Name;
  }
  //////////////////////
  @SimpleProperty
  @DesignerProperty(editorType = "text",defaultValue = "The Black Eyed Peas")
  public void ArtistName(String artist_Name){
    Artist_Name = artist_Name;
  }
  @SimpleProperty
  public String ArtistName(){
    return Artist_Name;
  }


  @SimpleFunction
  public void Initialize(String api){
    API = api;
    musixMatch = new MusixMatch(API);


    /////////////////////////////////////////////////// Track Search [ Fuzzy ] \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    try {
      track = musixMatch.getMatchingTrack(Track_Name, Artist_Name);
      data = track.getTrack();
    } catch (MusixMatchException e) {
      e.printStackTrace();
    }

    albumID = data.getAlbumId();
    albumName =  data.getAlbumName();
    artistID =  data.getArtistId();
    artistName =  data.getArtistName();
    trackID =  data.getTrackId();
    onFuzzySearch(albumID,albumName,artistID,artistName,trackID);

    ////////////////////////////////////////////////////////////////////  Getting Lyrics  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    try {
      lyrics = musixMatch.getLyrics(trackID);
    } catch (MusixMatchException e) {
      e.printStackTrace();
    }

    lyricsID =  lyrics.getLyricsId();
    lyricsLanguage = lyrics.getLyricsLang();
    lyricsBody = lyrics.getLyricsBody();
    scriptTrackingURL =  lyrics.getScriptTrackingURL();
    pixelTrackingURL =  lyrics.getPixelTrackingURL();
    lyricsCopyright = lyrics.getLyricsCopyright();

    onGettingLyrics(lyricsID,lyricsLanguage,lyricsBody,scriptTrackingURL,pixelTrackingURL,lyricsCopyright);


    //////////////////////////////////////////////////  Search Tracks   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



  }

  @SimpleEvent
  public void onGettingLyrics(int lyricsID, String lyricsLanguage, String lyricsBody, String scriptTrackingURL, String pixelTrackingURL, String lyricsCopyright) {
    EventDispatcher.dispatchEvent(this,"onGettingLyrics",lyricsID,lyricsLanguage,lyricsBody,scriptTrackingURL,pixelTrackingURL,lyricsCopyright);
  }

  @SimpleEvent
  public void onFuzzySearch(int albumID, String albumName, int artistID, String artistName, int trackID) {
    EventDispatcher.dispatchEvent(this,"onFuzzySearch",albumID,albumName,artistID,artistName,trackID);
  }




}
