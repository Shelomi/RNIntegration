import React from 'react'
import {
    Text,
    View,
    Button,
} from 'react-native'
import {TabNavigator} from 'react-navigation'

class RecentChatScreen extends React.Component{
    render(){
        return (
            <View>
                <Text> List of recent chats</Text>
                <Button title="Chat With Lucy"
                 onPress={()=>this.props.navigation.navigate('Chat',{user:'Lucy from China'})} />
            </View>
        )
    }
}

class AllContactChatScreen extends React.Component{
    render(){
        return (
            <View>
                <Text> List of all contacts</Text>
                <Button title="Chat With Lucy"
                        onPress={()=>this.props.navigation.navigate('Chat',{user:'Lucy from China'})} />
            </View>
        )
    }
}

const MainScreenNavigator = TabNavigator({
    Recent : {screen:RecentChatScreen},
    All:{screen:AllContactChatScreen},
});

MainScreenNavigator.navigationOptions = {
    title : 'My Chat',
};

export default MainScreenNavigator;