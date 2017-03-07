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
        return this.team;
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

    constructor(name: string ="none", imageUrl: string = "", masteryPoints: number = 0, masteryLevel: number = 1) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.masteryPoints = masteryPoints;
        this.masteryLevel = masteryLevel;
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

}

function addSummoner(summoner: Summoner) {
    let table = document.getElementById(summoner.getTeam() + "Team");
    let row = (<HTMLTableElement>table).insertRow(table.children.length);
    let length = row.cells.length;
    let currentChampionPlayed = summoner.getCurrentChampionPlayed();
    row.insertCell(length).innerHTML = summoner.getName(); //name
    row.insertCell(length).innerHTML= summoner.getRank();
    insertChampionImage(row, summoner.getCurrentChampionPlayed(), summoner.getTopThreeChampionsPlayed().prototype.every());
    const currentChampionImage = new Image();
    currentChampionImage.onload = function () {
        row.insertCell(length).innerHTML ='<img src=' + currentChampionPlayed.getImageUrl() + ' class="champ"> </br> ' + currentChampionPlayed.getName() + ' (' + currentChampionPlayed.getMasteryPoints() + ')';
    }
    currentChampionImage.onerror = function () {
        row.insertCell(length).innerHTML = 'Failed loading image. </br> ' + currentChampionPlayed.getName() + ' (' + currentChampionPlayed.getMasteryPoints() + ')';
    }
    currentChampionImage.src = currentChampionPlayed.getImageUrl(); //So the image starts loading
}
function insertChampionImage(row, ...championData: ChampionData[]) {
    const image = new Image();
    let rowLength = row.length;
    for (let champion of championData) {
        image.onload = function () {
            row.insertCell(rowLength).innerHTML = '<img src=' + champion.getImageUrl() + ' class="champ"> </br> ' + champion.getName() + ' (' + champion.getMasteryPoints() + ') and ' + champion.getMasteryLevel();
        }
        image.onerror = function () {
            row.insertCell(rowLength).innerHTML = 'Failed loading image. </br> ' + champion.getName() + ' (' + champion.getMasteryPoints() + ') and ' + champion.getMasteryLevel();
        }
        image.src = champion.getImageUrl(); //So the image starts loading
    }
}