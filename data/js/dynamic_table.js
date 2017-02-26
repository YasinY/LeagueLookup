 function addSummoner(team, name, currentChampionLink, currentChampionName, currentChampionMasteryPoints) {
         var table = document.getElementById(team);
         var row = table.insertRow(table.rows.length);
         var rowLength = row.length;
         row.insertCell(rowLength).innerHTML=name; //name
         var currentChampionImage = new Image();
         currentChampionImage.onload = function() {
             row.insertCell(rowLength).innerHTML=
             '<img src=' + currentChampionLink + ' class="champ"> </br> ' + currentChampionName + ' (' + currentChampionMasteryPoints + ')';
         }
         currentChampionImage.onerror = function() {
             row.insertCell(rowLength).innerHTML="Failed loading image </br> ' + currentChampionName + ' (' + currentChampionMasteryPoints + ')";
         }
         currentChampionImage.src = currentChampionLink; //So the image starts loading


     }

    function setCurrentChampion(team, row, link, name, masteryPoints) {
        document.getElementById(team).insertRow(0).insertCell(0).innerHTML = '<img src=' + link + ' id="champ"> </br> ' + name + ' (' + masteryPoints + ')';
    }

function setTopChampions(team, row, ids[]) {

}

function setRank(team, row, rank) {

}

function setKDA(team, row, kda) {

}


