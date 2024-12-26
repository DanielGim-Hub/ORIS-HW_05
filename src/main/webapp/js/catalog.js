// Логика фильтров
document.getElementById('searchInput').addEventListener('input', (e) => {
    console.log('Search:', e.target.value);
});

document.getElementById('regionFilter').addEventListener('change', (e) => {
    console.log('Region:', e.target.value);
});

document.getElementById('priceFilter').addEventListener('change', (e) => {
    console.log('Price:', e.target.value);
});
