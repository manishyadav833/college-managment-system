<script>

let toppers = [];

fetch('/data/topper-2021.json')

.then(res => res.json())

.then(data => {

    toppers = data;

    renderTable(toppers);

});

function renderTable(data) {

    const table = document.getElementById("topperTable");

    table.innerHTML = "";

    data.forEach(student => {

        table.innerHTML += `

        <tr class="hover:bg-white/5 transition">

            <td class="px-4 py-3 font-bold text-yellow-400">
                ${student.universityMeritNo}
            </td>

            <td class="px-4 py-3 text-white font-semibold">
                ${student.candidateName}
            </td>

            <td class="px-4 py-3">
                ${student.fatherName}
            </td>

            <td class="px-4 py-3">
                ${student.courseName}
            </td>

            <td class="px-4 py-3">
                ${student.semester}
            </td>

            <td class="px-4 py-3 text-green-400 font-bold">
                ${student.percentage}
            </td>

        </tr>

        `;

    });

}

function filterTable() {

    const search =
        document.getElementById("searchInput")
        .value
        .toLowerCase();

    const selectedCourse =
        document.getElementById("courseFilter").value;

    const selectedSemester =
        document.getElementById("semesterFilter").value;

    const filtered = toppers.filter(student => {

        const matchesSearch =
            student.candidateName
            .toLowerCase()
            .includes(search);

        const matchesCourse =
            selectedCourse === "all" ||
            student.courseName === selectedCourse;

        const matchesSemester =
            selectedSemester === "all" ||
            student.semester === selectedSemester;

        return matchesSearch &&
               matchesCourse &&
               matchesSemester;

    });

    renderTable(filtered);

}

document.getElementById("searchInput")
.addEventListener("keyup", filterTable);

document.getElementById("courseFilter")
.addEventListener("change", filterTable);

document.getElementById("semesterFilter")
.addEventListener("change", filterTable);

</script>