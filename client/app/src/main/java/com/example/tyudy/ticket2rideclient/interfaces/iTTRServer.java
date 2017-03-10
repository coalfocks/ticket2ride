package com.example.tyudy.ticket2rideclient.interfaces;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;

/**
 * Created by tyudy on 2/27/2017.
 * WARNING: IF YOU CHANGE THIS FILE YOU MUST MAKE THE SAME CHANGES ON THE SERVERS COPY - Ty
 */
public interface iTTRServer
{
    DataTransferObject createGame(DataTransferObject data);

    DataTransferObject startGame(DataTransferObject data);

    DataTransferObject endGame(DataTransferObject data);

    DataTransferObject listGames(DataTransferObject data);

    DataTransferObject getCommands(DataTransferObject data);

    DataTransferObject joinGame(DataTransferObject data);

    DataTransferObject login(DataTransferObject data);

    DataTransferObject register(DataTransferObject data);

    DataTransferObject initializeTrainCards(DataTransferObject data);

    DataTransferObject initializeDestinationCards(DataTransferObject data);

    DataTransferObject initializeChatRoom(DataTransferObject data);

    DataTransferObject sendChatMessage(DataTransferObject data);

    DataTransferObject updateGameplay(DataTransferObject data);
}