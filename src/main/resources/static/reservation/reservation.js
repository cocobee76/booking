window.reservation = (function () {

    const thead = document.querySelector(`#_thead`);
    const tbody = document.querySelector(`#_tbody`);
    const emp = document.querySelector(`#_emp`);
    const slot = document.querySelector(`#_slot`);
    const date = document.querySelector(`#date`);
    const check = document.querySelector(`#_check`);
    const room = document.querySelector(`#_room`)
    const repeatCount = document.querySelector(`#_repeatCount`);
    const submit = document.querySelector(`#_submit`);

    const totalSlotNo = 48;

    let rooms = [];
    let emps = [];
    let slots = [];

    let _init = () => {
        _initDatePicker();
        _initEmps();
        _initTimeSlots();
        _initModal();
    };

    let _initModal = () => {
        check.addEventListener(`change`, () => {
            repeatCount.disabled = !check.checked;
        });

        submit.addEventListener(`click`, (e) => {
            e.preventDefault();
            _submit();
        });
    };

    let _submit = () => {

        let validation = _validate();

        if (validation.result) {
            $.ajax({
                url: '/create',
                type: 'POST',
                data: {
                    startDate: date.value,
                    slotNo: slot.value,
                    roomNo: room.value,
                    empNo: emp.value,
                    repeatCount: check.checked ? repeatCount.value : null
                },
                dataType: 'text',
                beforeSend: function (jqXHR) {
                },
                success: function (jqXHR) {
                    $('#datepicker').val(date.value);
                    _getDailyReservation(date.value);
                    $('#_modal').modal('hide');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('실패' + jqXHR.responseText);
                },
                complete: function (jqXHR) {
                }
            });


        } else {
            alert(validation.msg);
        }
    };

    let _validate = () => {

        let ret = {result: true, msg: ""};

        if (!repeatCount.disabled) {

            let regNumber = /^[0-9]*$/;
            if(!regNumber.test(repeatCount.value)) {
                ret.result = false;
                ret.msg = "반복횟수는 숫자로 입력하세요."
            }
        }
        return ret;
    };

    let _getDailyReservation = (date) => {

        document.querySelectorAll('._slot').forEach(slot => {
            slot.innerHTML = "";
        });

        if (!date) {
            date = moment().format('YYYY[-]MM[-]DD');
        }

        $.ajax({
            type: 'GET',
            url: `/occupied?date=${date}`,
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    if (data.reservations) {
                        data.reservations.forEach((res) => {
                            let td = document.querySelector(`#td_${res.slotNo}_${res.roomNo}`);
                            td.innerHTML = emps[res.empNo].name;
                        });
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('실패' + jqXHR.responseText);
            }
        });
    };

    let _initGrid = () => {

        /* HEADER */
        const rows_header = [];
        const rows_room = [];

        rows_header.push(`<tr>`);
        rows_header.push(`<th scope="col">Time\\Room</th>`);

        for (let i = 0; i < rooms.length; i++) {
            rows_header.push(`<th scope="col">${rooms[i].name}</th>`)
            rows_room.push(`<option value="${rooms[i].id}">${rooms[i].name}</option>`)
        }

        rows_header.push(`</tr>`);

        thead.innerHTML = rows_header.join(`\n`);
        room.innerHTML = rows_room.join(`\n`);

        /* BODY */
        const rows_body = [];

        for (let i = 0; i < slots.length; i++) {
            rows_body.push(`<tr>`);
            rows_body.push(`<th scope="row">${slots[i]}</th>`);

            for (let j = 1; j <= rooms.length; j++) {
                rows_body.push(`<td class="_slot" id="td_${i}_${j}"></td>`)
            }
            rows_body.push(`</tr>`);
        }

        tbody.innerHTML = rows_body.join(`\n`);

        _getDailyReservation();

    };

    let _initDatePicker = () => {
        $('#datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 0,
            autoclose: true,
            todayHighlight: true,
        });

        $('#datepicker').val(moment().format('YYYY[-]MM[-]DD'));

        $('#datepicker').datepicker()
            .on('changeDate', e => {
                _getDailyReservation(e.format());
            });

        $('#date').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 0,
            autoclose: true,
            todayHighlight: true,
        });

        $('#date').val(moment().format('YYYY[-]MM[-]DD'));
    };

    let _initRooms = () => {
        $.ajax({
            type: 'GET',
            url: `/room`,
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    rooms = data;
                    _initGrid();
                } else {
                    console.log('내용이 없습니다');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('실패' + jqXHR.responseText);
            }
        });
    };

    let _initEmps = () => {
        $.ajax({
            type: 'GET',
            url: `/emp`,
            success: function (data, textStatus, jqXHR) {
                if (data) {
                    emps = data;

                    const rows = [];
                    emps.forEach((emp) => {
                        rows.push(`<option value="${emp.empNo}">${emp.name}</option>`)
                    });

                    emp.innerHTML = rows.join(`\n`);

                } else {
                    console.log('내용이 없습니다');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('실패' + jqXHR.responseText);
            }
        });
    };


    let _initTimeSlots = () => {

        const rows = [];

        for (let i = 0; i < totalSlotNo; i++) {
            let mo = moment();
            let start = mo.hours(0).minutes(0).seconds(0).add(30 * i, 'minutes').format('HH:mm');
            let end = mo.hours(0).minutes(0).seconds(0).add(30 * (i + 1), 'minutes').format('HH:mm');

            let timespan = start + " ~ " + end;
            slots.push(timespan);

            rows.push(`<option value="${i}">${timespan}</option>`);
        }

        slot.innerHTML = rows.join(`\n`);

        _initRooms();
    };

    return {
        init: _init
    }
})();