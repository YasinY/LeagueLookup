class Summoner {
    private name: string;
    private team: string;
    private rank: string;
    private currentChampionPlayed: ChampionData;
    private topThreeChampionsPlayed: ChampionData[];

    constructor(name: string, team: string, rank: string, currentChampionPlayed: ChampionData, ...topThreeChampionsPlayed: ChampionData[]) {
        this.name = name;
        this.team = team;
        this.rank = rank;
        this.currentChampionPlayed = currentChampionPlayed;
        this.topThreeChampionsPlayed = topThreeChampionsPlayed;
    }

    public getName() {
        return this.name;
    }

    public getTeam() {
        return this.team;
    }

    public getRank() {
        return this.rank;
    }

    public getCurrentChampionPlayed() {
        return this.currentChampionPlayed;
    }

    public getTopThreeChampionsPlayed() {
        return this.topThreeChampionsPlayed;
    }
}

class ChampionData {
    private name: string;
    private imageUrl: string;
    private masteryPoints: number;
    private masteryLevel: number;
    private winRate: number;
    private averageKDA: number;
    private averageCs: number;
    private gamesPlayed: number;


    constructor(name: string = "none", imageUrl: string = "", masteryPoints: number = 0, masteryLevel: number = 1, winRate: number = 0, averageKDA: number = 0, averageCs: number = 0, gamesPlayed: number = 0) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.masteryPoints = masteryPoints;
        this.masteryLevel = masteryLevel;
        this.winRate = winRate;
        this.averageKDA = averageKDA;
        this.averageCs = averageCs;
        this.gamesPlayed = gamesPlayed;
    }

    public getName() {
        return this.name;
    }

    public getImageUrl() {
        return this.imageUrl;
    }

    public getMasteryPoints() {
        return this.masteryPoints;
    }

    public getMasteryLevel() {
        return this.masteryLevel;
    }

    public getWinRate() {
        return this.winRate;
    }

    public getAverageKDA() {
        return this.averageKDA;
    }

    public getAverageCs() {
        return this.averageCs;
    }

    public getGamesPlayed() {
        return this.gamesPlayed;
    }

}

function addSummoner(summoner: Summoner = null) {
    if (summoner == null) {
        return;
    }
    let table = document.getElementById(summoner.getTeam() + "Team");
    let row = (<HTMLTableElement>table).insertRow(table.children.length); //table.rows.length!!!
    let currentChampionPlayed = summoner.getCurrentChampionPlayed();
    insertChampion(row, 0, currentChampionPlayed);
    row.insertCell(1).innerHTML = summoner.getName() + ': </br>  Win rate in %: ' + currentChampionPlayed.getWinRate() + ' (' + currentChampionPlayed.getGamesPlayed() + ') </br> Average KDA: ' + currentChampionPlayed.getAverageKDA() + ':1 </br> Average CS: ' + currentChampionPlayed.getAverageCs() + ''; //name //
    insertRank(row, summoner.getRank());
    insertChampion(row, 3, ...summoner.getTopThreeChampionsPlayed());
}
function insertRank(row, summonerRank) {
    let tier = summonerRank.split(" ")[0];
    let imageUrl = "http://sh0ck.bplaced.net/league_assets/rank_images/" + tier + ".png";
    let rank = new Image();
    let cell = row.insertCell(2);
    rank.onload = function () {
        cell.innerHTML = '<img class="rankImage" src=' + imageUrl + '>';
    }
    rank.onerror = function () {
        cell.innerHTML = summonerRank;
    }
    rank.src=imageUrl;
    //row.insertCell(2).innerHTML = "http://sh0ck.bplaced.net/league_assets/rank_images/mastery_" + summonerRank + ".png"
}
function insertChampion(row, rowIndex, ...champions: ChampionData[]) {
    let cell = row.insertCell(rowIndex);
    for(let champion of champions) {
        let champImage = new Image();
        let masteryImage = new Image();
        let masteryUrl = "http://sh0ck.bplaced.net/league_assets/championmastery_images/mastery_" + champion.getMasteryLevel() + ".png";
        masteryImage.onload = function () {
            champImage.onload = function () {
                cell.innerHTML += '<div class="champContainer"> <img class="mastery" src=' + masteryUrl + '> <img class="champion" src=' + champion.getImageUrl() + ' class="mastery"> </br> ' + champion.getName() + ' </br> (' + champion.getMasteryPoints() + ') </div>';
            };
            champImage.onerror = function () {
                cell.innerHTML += '</br><div class="champContainer">Failed loading image. </br> ' + champion.getName() + ' (' + champion.getMasteryPoints() + ') and ' + champion.getMasteryLevel() + "</div>";
            };
            champImage.src = champion.getImageUrl(); //So the image starts loading
        };
        masteryImage.onerror = function () {
            cell.innerHTML += 'Could not load champ mastery assets</br> ' + champion.getName() + ': Mastery points: ' + champion.getMasteryPoints() + ", Mastery level: " + champion.getMasteryLevel() + "</br>";
        };
        masteryImage.src = masteryUrl;
    }
}