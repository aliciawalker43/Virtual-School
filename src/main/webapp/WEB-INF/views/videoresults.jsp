<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Video Results</title>
</head>
<body>
<h2>Video Results...Did you find what you were looking for?</h2>
 
 <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
 <div id="player"></div>
 <br>
<div>   
      
      
        <c:forEach var="result" items= "${results }">
        
        
        <img src="${result.snippet.thumbnails.dfault.url}"><br>
        <p> ${result.snippet.title}<br>
        <button onclick="playVideo('${result.id.videoId}')">Play</button>
        
        </p>
        </c:forEach>
        </form>
</div>

<a href= "//">Return</a> 

  




    <script>
  
      // 2. This code loads the IFrame Player API code asynchronously.
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      // 3. This function creates an <iframe> (and YouTube player)
      //    after the API code downloads.
     
      function onYouTubeIframeAPIReady() {
       console.log("Youtube is ready!")
      }

      // 4. The API will call this function when the video player is ready.
      function onPlayerReady(event) {
        event.target.playVideo();
      }

      // 5. The API calls this function when the player's state changes.
      //    The function indicates that when playing a video (state=1),
      //    the player should play for six seconds and then stop.
      var done = false;
      function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
          setTimeout(stopVideo, 6000);
          done = true;
        }
      }
      function stopVideo() {
        player.stopVideo();
      }
      
      var player;
      function playVideo(vidId){
    	  if(player){
    		  player.destroy();
    	  }
    	  
    	  player = new YT.Player('player', {
              height: '390',
              width: '640',
              videoId: vidId ,
              events: {
                'onReady': onPlayerReady,
                'onStateChange': onPlayerStateChange
              }
            });
      }
    </script>

</body>
</html>