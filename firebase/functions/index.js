const functions = require('firebase-functions');
const admin = require('firebase-admin');
let db;
admin.initializeApp(functions.config().firebase);
db = admin.database();
exports.acknowledgeOrder = functions.database.ref(`debug/main/prdrive-orders/{campaign}/{transaction}`).onCreate(((snapshot, context) => {
    const newTransaction = snapshot.val();
    const campaignName = context.params.campaign;
    const transactionId = context.params.transaction;
    admin.database().ref(`/debug/main/prdrive-orders/${campaignName}/${transactionId}/acknowledge/`).set(true)
}));
