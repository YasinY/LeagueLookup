 function addName(team, row, name) {
            if(row == 0) {
            return;
            }
        document.getElementById(team).insertRow(row).insertCell(0).innerHTML=name;
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


