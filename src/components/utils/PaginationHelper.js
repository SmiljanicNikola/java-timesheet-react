const displayPaginated = async (pageNumber, size, getItemsFunc, displayItemsFunc) => {

    getItemsFunc(pageNumber, size).then(response => {
        displayItemsFunc(response.data.content);
    });
}

export default{
    displayPaginated
 };