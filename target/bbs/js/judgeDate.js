var date = {
    isDuringDate: function (beginDateStr, endDateStr) {
        var curDate = new Date(),
            beginDate = new Date(beginDateStr),
            endDate = new Date(endDateStr);
        if (curDate >= beginDate && curDate <= endDate) {
            return true;
        }
        return false;
    }
}